import React from 'react'

class BookingComponent extends React.Component
{


    constructor(props)
    {
        super(props);
        this.state = {rooms: []};
    }

    componentDidMount()
    {
        fetch("http://localhost:8080/rest/booking/getAllRooms").then(res => res.json())
            .then(result => {this.setState({rooms: result})})
    }

    render()
    {
        return (
            <div>
                Hallo
                {this.state.rooms.map(room => (
                    <tr key = {room.roomNumber}>
                        <td>{room.roomNumber}</td>
                    </tr>
                ))}
            </div>
        );
    }
}

export default BookingComponent;