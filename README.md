Swagger pod adresem:
http://localhost:8080/swagger-ui/

Dane wygenerowane za pomocą https://next.json-generator.com/

Przykładowe zapytania do API:

Funkjonalnośc 1:
curl -X GET "http://localhost:8080/cargo/statistics?date=Tue%20May%2004%202021%2015%3A01%3A57%20GMT%2B0000%20(UTC)&flightNumber=8360" -H "accept: application/json"
Funkcjonalność 2:
curl -X GET "http://localhost:8080/flight/statistics?iataCode=KRK" -H "accept: application/json"

Skrypty do generowania danych:
[
  {'repeat(5, 10)' :{
 flightId: '{{index()}}',
 baggageEntity: [
   {'repeat(3,8)' : {
 id: '{{index()}}',
 totalWeight: '{{integer(1, 999)}}',
 weightUnit: '{{random("kg","lb")}}',
 pieces: '{{integer(1, 999)}}'
   }
 }
 ],
 cargo: [
   {'repeat(3, 5)' : {
 id: '{{index()}}',
 totalWeight: '{{integer(1, 999)}}',
 weightUnit: '{{random("kg","lb")}}',
 pieces: '{{integer(1, 999)}}'
 }
   }
 ]
 }
}
]

[
  {'repeat(5, 10)':
 {
 flightId: '{{index()}}',
 flightNumber: '{{integer(1000, 9999)}}',
 departureAirportIATACode: '{{random("SEA","YYZ","YYT","ANC","LAX")}}',
 arrivalAirportIATACode: '{{random("MIT","LEW","GDN","KRK","PPX")}}',
 departureDate: '{{date(new Date(2014, 0, 1), new Date(), "YYYY-MM-ddThh:mm:ssZ")}}'
 }
  }
]