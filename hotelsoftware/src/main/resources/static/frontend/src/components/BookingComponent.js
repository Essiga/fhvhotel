import React from 'react'
import GuestComponent from "./GuestComponent";
import StayComponent from "./StayComponent";
import BookingSummaryComponent from "./BookingSummaryComponent";

class BookingComponent extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {
            guestDataExists: false,
            stayDataExists: false,
            gname: '',
            voucher: '',
            firstName: '',
            lastName: '',
            streetAdr: '',
            zip: '',
            city: '',
            country: '',
            phone: '',
            email: '',
            singleRoomCount: 0,
            doubleRoomCount: 0,
            superiorRoomCount: 0,
            checkInDate: null,
            checkOutDate: null
        };

        this.handleGnameChange = this.handleGnameChange.bind(this);
        this.handleVoucherChange = this.handleVoucherChange.bind(this);
        this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
        this.handleLastNameChange = this.handleLastNameChange.bind(this);
        this.handleStreetAdrChange = this.handleStreetAdrChange.bind(this);
        this.handleZipChange = this.handleZipChange.bind(this);
        this.handleCityChange = this.handleCityChange.bind(this);
        this.handleCountryChange = this.handleCountryChange.bind(this);
        this.handlePhoneChange = this.handlePhoneChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);

        this.handleSingleRoomCountChange = this.handleSingleRoomCountChange.bind(this);
        this.handleDoubleRoomCountChange = this.handleDoubleRoomCountChange.bind(this);
        this.handleSuperiorRoomCountChange = this.handleSuperiorRoomCountChange.bind(this);
        this.handleCheckInDateChange = this.handleCheckInDateChange.bind(this);
        this.handleCheckOutDateChange = this.handleCheckOutDateChange.bind(this);

        this.clearInputGuest = this.clearInputGuest.bind(this);
        this.checkInputGuest = this.checkInputGuest.bind(this);

        this.clearInputStay = this.clearInputStay.bind(this);
        this.checkInputStay = this.checkInputStay.bind(this);
    }

    handleGnameChange(value)
    {
        this.setState({gname: value});
    }

    handleVoucherChange(value)
    {
        this.setState({voucher: value});
    }

    handleFirstNameChange(value)
    {
        this.setState({firstName: value});
    }

    handleLastNameChange(value)
    {
        this.setState({lastName: value});
    }

    handleStreetAdrChange(value)
    {
        this.setState({streetAdr: value});
    }

    handleZipChange(value)
    {
        this.setState({zip: value});
    }

    handleCityChange(value)
    {
        this.setState({city: value});
    }

    handleCountryChange(value)
    {
        this.setState({country: value});
    }

    handlePhoneChange(value)
    {
        this.setState({phone: value});
    }

    handleEmailChange(value)
    {
        this.setState({email: value});
    }

    handleSingleRoomCountChange(value)
    {
        this.setState({singleRoomCount: value});
    }

    handleDoubleRoomCountChange(value)
    {
        this.setState({doubleRoomCount: value});
    }

    handleSuperiorRoomCountChange(value)
    {
        this.setState({superiorRoomCount: value});
    }

    handleCheckInDateChange(value)
    {
        this.setState({checkInDate: value});
    }

    handleCheckOutDateChange(value)
    {
        this.setState({checkOutDate: value});
    }

    clearInputGuest()
    {
        this.setState({gname: ''});
        this.setState({voucher: ''});
        this.setState({firstName: ''});
        this.setState({lastName: ''});
        this.setState({streetAdr: ''});
        this.setState({zip: ''});
        this.setState({city: ''});
        this.setState({country: ''});
        this.setState({phone: ''});
        this.setState({email: ''});

        this.setState({guestDataExists: false});
    }

    clearInputStay()
    {
        this.setState({singleRoomCount: 0});
        this.setState({doubleRoomCount: 0});
        this.setState({superiorRoomCount: 0});
        this.setState({checkInDate: null});
        this.setState({checkOutDate: null});

        this.setState({stayDataExists: false});
    }

    checkInputGuest()
    {
        if (this.state.gname === '')
            return;

        if (this.state.voucher === '')
            return;

        if (this.state.firstName === '')
            return;

        if (this.state.lastName === '')
            return;

        if (this.state.streetAdr === '')
            return;

        if (this.state.zip === '')
            return;

        if (this.state.city === '')
            return;

        if (this.state.country === '')
            return;

        if (this.state.phone === '')
            return;

        if (this.state.email === '')
            return;

        this.setState({guestDataExists: true});
    }

    checkInputStay()
    {
        if (this.state.singleRoomCount === 0 && this.state.doubleRoomCount === 0 && this.state.superiorRoomCount === 0)
            return

        if (this.state.checkInDate === null || this.state.checkOutDate === null)
            return;

        if (this.state.checkOutDate === null || this.state.checkOutDate === null)
            return;

        this.setState({stayDataExists: true});
    }

    render()
    {
        const guestDataExists = this.state.guestDataExists;
        const stayDataExists = this.state.stayDataExists;

        let component = <GuestComponent
                            gname = {this.state.gname}
                            voucher = {this.state.voucher}
                            firstName = {this.state.firstName}
                            lastName = {this.state.lastName}
                            streetAdr = {this.state.streetAdr}
                            zip = {this.state.zip}
                            city = {this.state.city}
                            country = {this.state.country}
                            phone = {this.state.phone}
                            email = {this.state.email}
                            onGnameChange = {this.handleGnameChange}
                            onVoucherChange = {this.handleVoucherChange}
                            onFirstNameChange = {this.handleFirstNameChange}
                            onLastNameChange = {this.handleLastNameChange}
                            onStreetAdrChange = {this.handleStreetAdrChange}
                            onZipChange = {this.handleZipChange}
                            onCityChange = {this.handleCityChange}
                            onCountryChange = {this.handleCountryChange}
                            onPhoneChange = {this.handlePhoneChange}
                            onEmailChange = {this.handleEmailChange}
                            onClearInputGuest = {this.clearInputGuest}
                            onCheckInputGuest = {this.checkInputGuest}
                        />

        if (guestDataExists)
        {
            component = <StayComponent
                            singleRoomCount = {this.state.singleRoomCount}
                            doubleRoomCount = {this.state.doubleRoomCount}
                            superiorRoomCount = {this.state.superiorRoomCount}
                            checkInDate = {this.state.checkInDate}
                            checkOutDate = {this.state.checkOutDate}
                            onSingleRoomCountChange = {this.handleSingleRoomCountChange}
                            onDoubleRoomCountChange = {this.handleDoubleRoomCountChange}
                            onSuperiorRoomCountChange = {this.handleSuperiorRoomCountChange}
                            onCheckInDateChange = {this.handleCheckInDateChange}
                            onCheckOutDateChange = {this.handleCheckOutDateChange}
                            onClearInputStay = {this.clearInputStay}
                            onCheckInputStay = {this.checkInputStay}
                        />
        }

        if (guestDataExists && stayDataExists)
        {
            component = <BookingSummaryComponent
                            gname = {this.state.gname}
                            voucher = {this.state.voucher}
                            firstName = {this.state.firstName}
                            lastName = {this.state.lastName}
                            streetAdr = {this.state.streetAdr}
                            zip = {this.state.zip}
                            city = {this.state.city}
                            country = {this.state.country}
                            phone = {this.state.phone}
                            email = {this.state.email}
                            singleRoomCount = {this.state.singleRoomCount}
                            doubleRoomCount = {this.state.doubleRoomCount}
                            superiorRoomCount = {this.state.superiorRoomCount}
                            checkInDate = {this.state.checkInDate}
                            checkOutDate = {this.state.checkOutDate}
                        />
        }

        return (
            <React.Fragment>
                {component}
            </React.Fragment>
        );
    }
}

export default BookingComponent;