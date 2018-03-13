using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Business.Interfaces
{
    public interface IMarkerServices
    {
        Marker AddMarker(Marker marker);
        List<Marker> GetMarkersArround(Marker m, double distance);
    }
}
