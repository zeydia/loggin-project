import React from 'react'
import { Link, Outlet } from 'react-router-dom'
import { MDBNavbar, MDBContainer, MDBNavbarLink, MDBBtn } from 'mdb-react-ui-kit'
import Logout from '../logout/Logout';

const Layout = () => {
    const HomeLink = "/" + (document.URL.includes('user') ? "user" : "admin");
    return (
        <div>
            <div className='mb-4'>
                <MDBNavbar light bgColor='light'>
                    <MDBContainer className='p-2'>
                        <Link to={HomeLink} className='me-2'>HOME</Link>
                        <Logout />
                    </MDBContainer>
                </MDBNavbar>
            </div>
            <Outlet />
        </div>
    )
}

export default Layout