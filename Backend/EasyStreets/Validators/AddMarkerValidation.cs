using Domain.Entities;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EasyStreets.Validators
{
    public class AddMarkerValidation: AbstractValidator<Marker>
    {
        public AddMarkerValidation()
        {
            RuleFor(x => x.Latitude).NotNull().WithMessage("Latitude não pode ser nulo");
            RuleFor(x => x.Longitude).NotNull().WithMessage("Longitude não pode ser nulo");
            RuleFor(x => x.Description).MaximumLength(300).WithMessage("A descrição não pode ser nula ou vazia e deve ter no máximo 300 caracteres");
        }
    }
}
