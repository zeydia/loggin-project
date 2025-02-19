import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { MDBBtn, MDBContainer, MDBInput, MDBInputGroup, MDBNavbar, MDBNavbarItem, MDBNavbarLink, MDBNavbarNav, MDBNavbarBrand } from 'mdb-react-ui-kit'

const InfoView = () => {
    const [state, setState] = useState({
        fullName: 'seydou',
        username: 'zeydia',
        email: 'seydou@email.com',
        password: '',
        mobile: '777777777',
        role: '',
        error: ''
    })
    const history = useNavigate()

    const handleSubmit = () => null;

    const handlePassword = () => {

        alert("Vous etes entrain de modifier votre mot de passe.")
    }

    return (

            <div className="d-flex justify-content-center align-items-center">

                <div className="card p-4" style={{ width: "1000px", height: "auto" }}>
                    <MDBContainer className="p-3">
                        {/* PAGE TITLE */}
                        <h2 className="text-center mb-4">My Informations</h2>

                        {/* ERROR */}
                        {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

                        {/* ROW 1 */}
                        <div className='d-flex mb-3'>

                            {/* FULLNAME */}
                            <div className="d-flex flex-column me-3 flex-fill">
                                <label htmlFor='fullname'>FullName</label>
                                <MDBInputGroup> 
                            <input className='form-control' id='fullName' placeholder='fullName' type='text' value={state.fullName}
                                onChange={(e) => {
                                    console.log(e.target.id)
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
                                    console.log(e.target.id)
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
                                            console.log(e.target.id)
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }}>
                                    </input>
                                </MDBInputGroup>
                            </div>

                            {/* MOBILE */}
                            <div className="d-flex flex-column flex-fill">
                                <label htmlFor='mobile'>mobile</label>
                                <MDBInputGroup textBefore='+221' label='mobile'>
                                    <input className='form-control' id='mobile' placeholder='mobile' type='tel' value={state.mobile}
                                        onChange={(e) => {
                                            console.log(e.target.id)
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }} />
                                </MDBInputGroup>
                            </div>
                        </div>

                        {/* MOBILE */}
                        <div className="d-flex flex-column mb-3">
                                <label htmlFor='password'>Change password</label>
                                <MDBInputGroup>
                                    <input className='form-control' id='password' type='text' value={state.password}
                                        placeholder='By filling this field your are modifiying your password' 
                                        onChange={(e) => {
                                            console.log(e.target.id)
                                            setState({ ...state, [e.target.id]: e.target.value })
                                        }} />
                                        <MDBBtn onClick={handlePassword}>Change password</MDBBtn>
                                </MDBInputGroup>
                            </div>


                        {/* ROLE */}
                        <div className='flex'>
                            <label>Role</label>
                            <select className="form-select mb-4" id='role' value={state.role}
                                onChange={(e) => {
                                    console.log(e.target.id)
                                    setState({ ...state, [e.target.id]: e.target.value })
                                }}>
                                <option value="ROLE_USER">User</option>
                                <option value="ROLE_ADMIN">Admin</option>
                            </select>
                        </div>



                        {/* SUBMIT */}
                        <MDBBtn className='mb-3'
                            style={{ height: '40px', width: '100%' }}
                            onClick={handleSubmit}>Update my Informations
                        </MDBBtn>

                    </MDBContainer>
                </div>
            </div>
    
    )
}

export default InfoView