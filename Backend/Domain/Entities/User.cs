using System.Collections.Generic;

namespace Domain.Entities
{
    public class User : Identifiable<long>
    {
        public User()
        {
            this.UserRoles = new List<UserRole>();
        }

        public long ID { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }

        public virtual ICollection<UserRole> UserRoles { get; set; }

        #region Object overrides
        public override bool Equals(object obj)
        {
            if (obj is User)
            {
                var user = obj as User;
                return user.ID.Equals(this.ID) &&
                       user.Email.Equals(this.Email) &&
                       user.Name.Equals(this.Name) &&
                       user.Password.Equals(this.Password) &&
                       user.UserRoles.Equals(this.UserRoles);
            }
            else return false;

        }
        #endregion
    }
}
