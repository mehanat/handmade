CREATE TABLE  IF NOT EXISTS sessions (
  ID TEXT PRIMARY KEY,
  USER_ID INTEGER REFERENCES app_user(id),
  EXPIRATION TIMESTAMP
);