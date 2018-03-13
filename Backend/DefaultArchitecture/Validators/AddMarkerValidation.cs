using Domain.Entities;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DefaultArchitecture.Validators
{
    public class AddMarkerValidation: AbstractValidator<Marker>
    {
        public AddMarkerValidation()
        {
            RuleFor(x => x.Latitude).NotNull().WithMessage("Latitude não pode ser nulo");
            RuleFor(x => x.Longitude).NotNull().WithMessage("Longitude não pode ser nulo");
            RuleFor(x => x.Title).NotNull().NotEmpty().MaximumLength(45).WithMessage("O Título não pode ser nulo ou vazio e deve ter no máximo 45 caracteres");
            RuleFor(x => x.Description).NotNull().NotEmpty().MaximumLength(300).WithMessage("A descrição não pode ser nula ou vazia e deve ter no máximo 300 caracteres");
        }
    }
}
