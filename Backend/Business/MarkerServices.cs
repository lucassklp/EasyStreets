using System;
using System.Collections.Generic;
using System.Text;
using Business.Interfaces;
using Domain.Entities;
using Repository.Interfaces;

namespace Business
{
    public class MarkerServices : IMarkerServices
    {
        IMarkerRepository repository;
        public MarkerServices(IMarkerRepository repository)
        {
            this.repository = repository;
        }

        public Marker AddMarker(Marker marker)
        {
            // Regras serão adicionadas aqui...
            return this.repository.Insert(marker);
        }

        public List<Marker> GetMarkersArround(Marker m, double distance)
        {
            // Regras de cálculo de distância aqui...

        }
    }
}
