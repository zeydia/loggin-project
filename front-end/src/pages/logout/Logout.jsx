import { MDBBtn } from 'mdb-react-ui-kit'
import { useNavigate } from 'react-router-dom'
import { removeCookie } from '../../utils/Cookies'

const Logout = () => {

    const history = useNavigate();

    const handleLogout = async () => {
            removeCookie('LOGIN_INFO')
            history("/")
    }

    return (
        <div>
            <MDBBtn className='bg-danger' onClick={handleLogout}>Deconnexion</MDBBtn>

        </div>
    )
}

export default Logout