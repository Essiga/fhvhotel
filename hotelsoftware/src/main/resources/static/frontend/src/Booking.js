import React from 'react'

class Booking extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {rooms: []};
    }

    componentDidMount()
    {
        fetch("http://localhost:8080/getAllRooms").then(res => res.json())
            .then(result => {this.setState({rooms: result})})
    }
//<meta http-equiv="Access-Control-Allow-Origin" content="*"/>
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

export default Booking;