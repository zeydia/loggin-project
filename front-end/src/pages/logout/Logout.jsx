import React from 'react'
import { MDBBtn } from 'mdb-react-ui-kit'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const Logout = () => {

    const history = useNavigate();

    const handleLogout = async () => {
        try {
            axios.get("http://localhost:8080/logout")
                .then(
                    history("/login")
                )
        } catch (error) {
            
        }

    }

    return (
        <div>
            <MDBBtn className='bg-danger' onClick={handleLogout}>Logout</MDBBtn>

        </div>
    )
}

export default Logout