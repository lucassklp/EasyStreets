using Domain.Dtos;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EasyStreets.Validators
{
    public class MarkersAroundParametersValidation : AbstractValidator<MarkersAroundParametersDto>
    {
        public MarkersAroundParametersValidation()
        {
            RuleFor(x => x.Distance).NotNull();
            RuleFor(x => x.Latitude).NotNull();
            RuleFor(x => x.Longitude).NotNull();
        }
    }
}
