import { useEffect } from 'react'
import { Link, Outlet, useNavigate } from 'react-router-dom'
import { MDBNavbar, MDBContainer, } from 'mdb-react-ui-kit'
import Logout from '../logout/Logout';
import axios from 'axios';
import { getCookie } from '../../utils/Cookies';

const Layout = () => {
    const api = import.meta.env.VITE_API;
    const url = import.meta.env.VITE_URL;
    const history = useNavigate();
    const token = getCookie('LOGIN_INFO');

    useEffect(() => {
        if((document.URL == url || document.URL == url+"/") && token){
            axios.get(api+"/user",{
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            .then((response) => {
                const role = response.data.role;
                if (role.includes('USER')) 
                    history("/user")
                else if (role.includes('ADMIN'))
                    history("/admin")
            })
            .catch((error) => console.error(error))
        } else {
            history("/home")
        }
        
    },[])
    


    const HomeLink = "/" + (document.URL.includes('USER') ? "user" : "admin");
    return (
        <div>
            <div className='mb-4'>
                <MDBNavbar light bgColor='light'>
                    <MDBContainer className='p-2'>
                        <Link to={HomeLink} className='me-2'>RETOUR A L&apos;ACCEUIL</Link>
                        <Logout />
                    </MDBContainer>
                </MDBNavbar>
            </div>
            <Outlet />
        </div>
    )
}

export default Layout