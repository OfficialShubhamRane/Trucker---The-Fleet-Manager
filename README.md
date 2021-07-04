# Trucker---The-Fleet-Manager
Fleet Managment System:
    * Built a REST API-based Fleet Management Service to increase productivity to run businesses smoothly 
    * Developed a feature that monitors condition-based activities, triggering an Email alert notification
    * Provided real-time insights for operations of Fleet performance focusing on Driver Safety, Cost Reduction, ELD Complied Fleet Tracking
    
The purpose of fleet management is to oversee all fleet  performance and fleet maintenance in order to increase productivity.

Deliveriables:
1. Develop following REST endpoints for ingestion from: [http://mocker.egen.io]
    1. Load vehicle details in bulk via a PUT /vehicles endpoint.
    2. If the vehicle with same VIN is already present, update the record in db.
    3. Ingest readings from these vehicles via a POST /readings.
2. Create alerts with given priority when following rules are triggered
    * Rule: engineRpm > readlineRpm, Priority: HIGH
    * Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
    * Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
    * Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW
3. Develop User Interface with following features
    * Ability to see details of all the vehicles like VIN, make, model, year etc.
    * Show number of HIGH alerts within last 2 hours for all the vehicles and ability to sort list of vehicles based on it.
    * Ability to plot vehicle's history of specific signal over a user selected time range, e.g. fuelVolume over 2 hours, engineRpm over last   15minutes.
    * Ability to show vehicle's geolocation within last 30minutes on a map.
    * Ability to list a vehicle's all historical alerts.
    * OPTIONAL: Ability to send an email to the user when HIGH alerts are triggered for a vehicle.


Sample Data:
    Vehicle Details: 
    Make PUT request on http://localhost:8080/vehicles
       
            {
                "vin": "1HGCR2F3XFA027534",
                "make": "HONDA",
                "model": "ACCORD",
                "year": 2015,
                "redlineRpm": 5500,
                "maxFuelVolume": 15,
                "lastServiceDate": "2017-05-25T17:31:25.268Z"
            },
            {
                "vin": "WP1AB29P63LA60179",
                "make": "PORSCHE",
                "model": "CAYENNE",
                "year": 2015,
                "redlineRpm": 8000,
                "maxFuelVolume": 18,
                "lastServiceDate": "2017-03-25T17:31:25.268Z"
            }

    Vehicle Reading:
    Make POST request on http://localhost:8080/readings
        {
            "vin": "1HGCR2F3XFA027534",
            "latitude": 41.803194,
            "longitude": -88.144406,
            "timestamp": "2017-05-25T17:31:25.268Z",
            "fuelVolume": 1.5,
            "speed": 85,
            "engineHp": 240,
            "checkEngineLightOn": false,
            "engineCoolantLow": true,
            "cruiseControlOn": true,
            "engineRpm": 6300,
            "tires": {
                "frontLeft": 34,
                "frontRight": 36,
                "rearLeft": 29,
                "rearRight": 34
            }
        }
