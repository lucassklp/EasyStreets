using Domain.Entities;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DefaultArchitecture.Validators
{
    public class LoginUserValidation : AbstractValidator<User>
    {
        public LoginUserValidation()
        {
            RuleFor(x => x.Email).EmailAddress().NotNull().NotEmpty();
            RuleFor(x => x.Password).MinimumLength(6);
        }
    }
}
