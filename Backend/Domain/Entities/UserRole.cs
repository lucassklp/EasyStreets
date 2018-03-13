namespace Domain.Entities
{
    public class UserRole
    {
        public long UserId { get; set; }
        public User User { get; set; }
        public long RoleId { get; set; }
        public Role Role { get; set; }

        public override bool Equals(object obj)
        {
            if(obj is UserRole)
            {
                var userRole = obj as UserRole;
                return this.RoleId.Equals(userRole.RoleId) &&
                       this.UserId.Equals(userRole.UserId);
            }
            else return false;
        }
    }
}
