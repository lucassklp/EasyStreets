using Business.Interfaces;
using DefaultArchitecture.Validators;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace DefaultArchitecture.Controllers
{
    [AllowAnonymous]
    [Route("api/marker")]
    public class MarkerController : Controller
    {
        IMarkerServices markerServices;
        public MarkerController(IMarkerServices markerService)
        {
            this.markerServices = markerService;
        }

        [HttpGet]
        public IActionResult GetMarkers()
        {
            return this.markerServices.GetMarkersArround()
        }

        [HttpPost]
        public IActionResult AddMarker([FromBody] Marker marker)
        {
            var validation = new AddMarkerValidation();
            var results = validation.Validate(marker);
            if (results.IsValid)
            {
                var inserted = this.markerServices.AddMarker(marker);
                return Ok(inserted);
            }
            else
            {
                return BadRequest(results.Errors);
            }
        }
    }
}
