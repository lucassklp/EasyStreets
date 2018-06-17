using Business.Interfaces;
using EasyStreets.Validators;
using Domain.Dtos;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace EasyStreets.Controllers
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
        public IActionResult ListMarkers()
        {
            return Ok(this.markerServices.List());
        }

        [HttpGet]
        [Route("around")]
        public IActionResult GetMarkersAround([FromBody] MarkersAroundParametersDto param)
        {
            var validation = new MarkersAroundParametersValidation();
            var results = validation.Validate(param);
            if (results.IsValid)
            {
                return Ok(this.markerServices.GetMarkersArround(param));
            }
            else return BadRequest(results.Errors);
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

        [HttpPut]
        public IActionResult UpdateMarker([FromBody] Marker marker)
        {
            this.markerServices.Update(marker);
            return Ok();
        }

        [HttpDelete("{id}")]
        public IActionResult RemoveMarker(long id)
        {
            this.markerServices.Remove(id);
            return Ok();
        }
    }
}
