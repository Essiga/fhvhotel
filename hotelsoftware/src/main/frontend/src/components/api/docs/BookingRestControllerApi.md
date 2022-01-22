# OpenApiDefinition.BookingRestControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createBooking**](docs/BookingRestControllerApi.md#createBooking) | **POST** /rest/booking/createBooking | 
[**createGuest**](docs/BookingRestControllerApi.md#createGuest) | **POST** /rest/booking/createGuest | 
[**getRoomPrices**](docs/BookingRestControllerApi.md#getRoomPrices) | **GET** /rest/booking/getRoomPrices | 
[**getTotalRoom**](docs/BookingRestControllerApi.md#getTotalRoom) | **POST** /rest/booking/getTotalRoom | 



## createBooking

> BookingId createBooking(bookingDataDTO)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.BookingRestControllerApi();
let bookingDataDTO = new OpenApiDefinition.BookingDataDTO(); // BookingDataDTO | 
apiInstance.createBooking(bookingDataDTO, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bookingDataDTO** | [**BookingDataDTO**](docs/BookingDataDTO.md)|  | 

### Return type

[**BookingId**](docs/BookingId.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*


## createGuest

> GuestId createGuest(guestDTO)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.BookingRestControllerApi();
let guestDTO = new OpenApiDefinition.GuestDTO(); // GuestDTO | 
apiInstance.createGuest(guestDTO, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **guestDTO** | [**GuestDTO**](docs/GuestDTO.md)|  | 

### Return type

[**GuestId**](docs/GuestId.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*


## getRoomPrices

> RoomPriceDTO getRoomPrices()



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.BookingRestControllerApi();
apiInstance.getRoomPrices((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**RoomPriceDTO**](docs/RoomPriceDTO.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


## getTotalRoom

> [Number] getTotalRoom(bookingForm)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.BookingRestControllerApi();
let bookingForm = new OpenApiDefinition.BookingForm(); // BookingForm | 
apiInstance.getTotalRoom(bookingForm, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **bookingForm** | [**BookingForm**](docs/BookingForm.md)|  | 

### Return type

**[Number]**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

