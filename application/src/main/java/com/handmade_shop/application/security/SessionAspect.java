package com.handmade_shop.application.security;


import com.handmade_shop.domain.user.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class SessionAspect {

    private final SessionRepository sessionRepository;
    private final AuthorizedUserService authorizedUserService;

    @Autowired
    public SessionAspect(SessionRepository sessionRepository,
                         AuthorizedUserService authorizedUserService) {
        this.sessionRepository = sessionRepository;
        this.authorizedUserService = authorizedUserService;
    }

    /**
     * Выполняет внедрение Сессии для public методов содержащих аргумент с аннотацией InjectSession
     *
     * @param proceedingJoinPoint - точка среза аспекта с возможностью продолжить работу метода, передав аргументы
     * @return продолжает работу метода передавая измененный массив аргументов с уже внедренной Сессией
     * @throws Throwable - при внутренней ошибке AspectJ
     */
    @Around(value = "execution(public * *(.., @com.handmade_shop.application.security.InjectSession (*), ..))")
    public Object injectSession(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Session session = safeGet(args);
        int index = Arrays.asList(args).indexOf(session);
        args[index] = inject();
        return proceedingJoinPoint.proceed(args);
    }

    /**
     * Выполняет обновление сессии после успешного завершения работы метода
     *
     * @param joinPoint - точка среза аспекта
     */
    @AfterReturning(value = "execution(public * *(.., @com.handmade_shop.application.security.InjectSession (*), ..))")
    public void updateSession(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Session session = safeGet(args);
        sessionRepository.save(session);
    }

    /**
     * Возвращает новую или существующую сессию из базы данных для текущего пользователя
     *
     * @return новая или существующая сессия
     */
    private Session inject() {
        User user = authorizedUserService.get();
        return Objects.isNull(user)
                ? sessionRepository.save(Session.init())
                : sessionRepository.get("af");
    }

    /**
     * Ищет среди аргументов метода объект класса Session делает кастинг и возвращает его
     *
     * @return объект класса Сессии
     * @throws IllegalArgumentException если объект класса Сессии не найден в аргументах метода
     */
    private Session safeGet(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof Session)
                .findAny()
                .map(arg -> (Session) arg)
                .orElseThrow(() -> new IllegalArgumentException("No session found in method args"));
    }

}
