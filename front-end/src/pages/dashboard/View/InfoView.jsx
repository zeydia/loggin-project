import { useEffect, useState } from 'react';
// import { useNavigate } from 'react-router-dom';
import { MDBBtn, MDBContainer, MDBInputGroup } from 'mdb-react-ui-kit'
import axios from 'axios';
import { getCookie } from '../../../utils/Cookies';

const InfoView = () => {
    const api = import.meta.env.VITE_API;
    const token = getCookie("LOGIN_INFO");
    const [state, setState] = useState({
        fullname: '',
        username: '',
        email: '',
        password: '',
        mobile: '',
        role: '',
        error: ''
    })
//    const history = useNavigate();

    useEffect(() => {
        axios.get(api + "/user", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then((response) => setState({
            ...state,
            username: response.data.username,
            fullname: response.data.fullname,
            email: response.data.email,
            mobile: response.data.mobile,
            role: (response.data.role == 'ROLE_ADMIN' ) ? 'admin' : 'user',
        }))
        .catch((error) => console.log(error))
    },[])

    const handleSubmit = async () => {
        if (confirm("Voulez vous mettre à jour vos informations?")){
            const {fullname,username,email,password,mobile,role} = state;

            const response = await axios.post(api + "/user/updateUser",{fullname,username,email,password,mobile,role},
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            })
            .then((res) => console.log(res))
        }

        
    };

    const handlePassword = async () => {
        const confirmPass = prompt("Confirmer votre mot de passe");

        if (state.password == confirmPass){
            await axios.post(api + "/user/renewPass", {
                headers: {
                    Authorization: `Bearer ${token}`
                },
                data: confirmPass
            })
            .then(alert("mot de passe mis a jour!"))
            .catch((error) => console.error(error))
        }
        
    }

    return (

            <div className="d-flex justify-content-center align-items-center">

                <div className="card p-4" style={{ width: "1000px", height: "auto" }}>
                    <MDBContainer className="p-3">
                        {/* PAGE TITLE */}
                        <h2 className="text-center mb-4">Mes Informations</h2>

                        {/* ERROR */}
                        {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

                        {/* ROW 1 */}
                        <div className='d-flex mb-3'>

                            {/* FULLNAME */}
                            <div className="d-flex flex-column me-3 flex-fill">
                                <label htmlFor='fullname'>Nom Complet</label>
                                <MDBInputGroup> 
                            <input className='form-control' id='fullName' placeholder='fullName' type='text' value={state.fullname}
                                onChange={(e) => {
                                    setState({ ...state, [e.target.id]: e.target.value })
                                }}/>
                            </MDBInputGroup>
                            </div>

                            {/* USERNAME */}
                            <div className="d-flex flex-column flex-fill">
                                <label htmlFor='username'>Username</label>
                                <MDBInputGroup >
                            <input className='form-control' id='username' placeholder='username' type='text' value={state.username}
                                onChange={(e) => {
                                    setState({ ...state, [e.target.id]: e.target.value })
                                }}/>
                            </MDBInputGroup>
                            </div>
                        </div>

                        {/* ROW 2 */}
                        <div className='d-flex mb-3'>

                            {/* EMAIL */}
                            <div className="d-flex flex-column me-3 flex-fill">
                                <label htmlFor='email'>Email</label>
                                <MDBInputGroup >
                                    <input className='form-control' id='email' placeholder='email' type='text' value={state.email}
                                        onChange={(e) => {
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }}>
                                    </input>
                                </MDBInputGroup>
                            </div>

                            {/* MOBILE */}
                            <div className="d-flex flex-column flex-fill">
                                <label htmlFor='mobile'>Mobile</label>
                                <MDBInputGroup textBefore='+221' label='mobile'>
                                    <input className='form-control' id='mobile' placeholder='mobile' type='tel' value={state.mobile}
                                        onChange={(e) => {
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }} />
                                </MDBInputGroup>
                            </div>
                        </div>

                        {/* MOBILE */}
                        <div className="d-flex flex-column mb-3">
                                <label htmlFor='password'>Changer votre mot de passe</label>
                                <MDBInputGroup>
                                    <input className='form-control' id='password' type='text' value={state.password}
                                        placeholder='By filling this field your are modifiying your password' 
                                        onChange={(e) => {
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }} />
                                        <MDBBtn onClick={handlePassword}>valider</MDBBtn>
                                </MDBInputGroup>
                            </div>


                        {/* ROLE */}
                        <div className='flex'>
                            <label>Role</label>
                            <select className="form-select mb-4" id='role' value={state.role}
                                onChange={(e) => {
                                    setState({ ...state, [e.target.id]: e.target.value })
                                }}>
                                <option value="ROLE_USER">User</option>
                                <option value="ROLE_ADMIN">Admin</option>
                            </select>
                        </div>



                        {/* SUBMIT */}
                        <MDBBtn className='mb-3'
                            style={{ height: '40px', width: '100%' }}
                            onClick={handleSubmit}>Mettre à jour mes informations
                        </MDBBtn>

                    </MDBContainer>
                </div>
            </div>
    
    )
}

export default InfoView