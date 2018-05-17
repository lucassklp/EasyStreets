using System;
using System.Collections.Generic;
using System.Text;

namespace Domain.Entities
{
    public enum StreetFurniture : int
    {
        BusStop,                        //pontos de ônibus
        TaxiPoints,                     //pontos de táxi
        MailBoxes,                      //caixas de coleta de correio
        Hydrants,                       //hidrantes
        TelephoneNetworkCabinets,       //armários da rede telefônica
        ElectricalNetworkCabinets,      //armários da rede elétrica
        Seats,                          //bancos com ou sem costas
        Vase,                           //vasos
        Trash,                          //lixeiras ou papeleiras
        LightingPoles,                  //postes de iluminação
        PowerPoles,                     //postes da rede elétrica
        SignPosts,                      //postes de sinalização
        BikeSpot,                       //apoios ou parqueamento de bicicletas
        Beacon,                         //divisores, guias e balizadores (fradinhos, pilones, etc)
        WaterSource,                    //fontes ou bebedouros
        Newsstands,                     //bancas de jornal
        FlowerStands,                   //bancas de flores ou floreiras
        Clocks,                         //relógios
        TablesWithBenches,              //mesas com bancos
        Handrails,                      //guardas e corrimãos
        Grills,                         //grelhas para caldeiras de árvores
        ShadingStructures,              //estruturas de sombreamento
        Dispenser,                      //dispensador de sacos para dejetos caninos
        InformationMedia,               //suportes informativos e expositores
        GymStructures                   //estruturas de ginástica para seniores
    }
}
