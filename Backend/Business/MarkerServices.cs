using System;
using System.Collections.Generic;
using System.Text;
using Business.Interfaces;
using Domain.Dtos;
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

        public List<Marker> GetMarkersArround(MarkersAroundParametersDto param)
        {
            // Regras de cálculo de distância aqui...
            // Mockado por enquanto:
            //return this.repository.SelectAll();
            throw new NotImplementedException();
        }

        public List<Marker> List()
        {
            return this.repository.SelectAll();
        }
    }
}
