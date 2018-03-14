namespace Domain.Entities
{
    public class Marker : Identifiable<long>
    {
        public long ID { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public string Description { get; set; }
        public string Title { get; set; }
        public StreetFurniture StreetFurniture { get; set; }
    }
}
