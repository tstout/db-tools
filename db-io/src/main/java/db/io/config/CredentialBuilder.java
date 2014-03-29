package db.io.config;

public interface CredentialBuilder {
    CredentialBuilder withUser(DBUser user);
    CredentialBuilder withUrl(DBUrl url);
    CredentialBuilder withPwd(DBPwd pwd);
    DBCredentials build();
}
