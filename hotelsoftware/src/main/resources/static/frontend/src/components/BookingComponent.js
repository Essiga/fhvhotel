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
            roomDataExists: false,
            gname: '',
            voucher: '',
            firstName: '',
            lastName: '',
            streetAdr: '',
            zip: '',
            city: '',
            country: '',
            phone: '',
            email: ''
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

        this.clearInputGuest = this.clearInputGuest.bind(this);
        this.checkInputGuest = this.checkInputGuest.bind(this);
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

    render()
    {
        const guestDataExists = this.state.guestDataExists;
        const roomDataExists = this.state.roomDataExists;
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
            component = <StayComponent/>
        }

        if (guestDataExists && roomDataExists)
        {
            component = <BookingSummaryComponent/>
        }

        return (
            <React.Fragment>
                {component}
            </React.Fragment>
        );
    }
}

export default BookingComponent;