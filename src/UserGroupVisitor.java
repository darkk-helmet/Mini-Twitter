public interface UserGroupVisitor {
    void visitUser(User user, String s);
    void visitUserGroup(UserGroup userGroup, String s);
    void visitUserDatabase(UserDatabase userDatabase, String s);
}
