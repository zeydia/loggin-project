import axios from 'axios'
import { MDBBtn, MDBContainer, MDBInput, MDBInputGroup } from 'mdb-react-ui-kit'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const Signup = () => {

  const [state, setState] = useState({
    fullName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    mobile: '',
    role: '',
    error: ''
  })
  const history = useNavigate()

  const handleSubmit = async () => {

    try {
      const { fullName, username, email, password, confirmPassword, mobile, role } = state

      if (fullName == '', username == '', email == '', password == '', confirmPassword == '', mobile == '', role == '') {
        setState({ ...state, error: 'Please fill all the fields' })
      }

      if (password !== confirmPassword) {
        setState({ ...state, error: 'Password and Confirm Password should be same' })
      }

      const response = await axios.post('', { fullName, username, email, password, confirmPassword, mobile, role })
      console.log(response.data)
      history('/dashbord')
    }
    catch (error) {
      console.error('Signup failed:', error.response ? error.response.data : error.message);
      setError(error.response ? error.response.data : error.message);
    }

  }


  return (
    <>
      <a href='/' className=''>
        <MDBBtn>HOME</MDBBtn>
      </a>

      <div className="d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4" style={{ width: "600px", height: "auto" }}>
          <MDBContainer className="p-3">
            {/* SIGN UP TITLE */}
            <h2 className="text-center mb-4">Sign Up</h2>

            {/* ERROR */}
            {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

            {/* FULLNAME */}
            <MDBInput wrapperClass='mb-3' id='fullName' label='fullName' type='text' value={state.fullName}
              onChange={(e) => {
                console.log(e.target.id)
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* USERNAME */}
            <MDBInput wrapperClass='mb-3' id='username' label='username' type='text' value={state.username}
              onChange={(e) => {
                console.log(e.target.id)
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* EMAIL */}
            <MDBInput wrapperClass='mb-3' id='email' label='email' type='text' value={state.email}
              onChange={(e) => {
                console.log(e.target.id)
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* PASSWORD */}
            <MDBInput wrapperClass='mb-3' id='password' label='password' type='password' value={state.password}
              onChange={(e) => {
                console.log(e.target.id)
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* CONFIRM PASSWORD */}
            <MDBInput wrapperClass='mb-3' id='confirmPassword' label='confirmPassword' type='password' value={state.confirmPassword}
              onChange={(e) => {
                console.log(e.target.id)
                setState({ ...state, [e.target.id]: e.target.value })
              }}>
            </MDBInput>

            {/* MOBILE */}
            <MDBInputGroup textBefore='+221'>
              <MDBInput wrapperClass='mb-3' id='mobile' label='mobile' type='tel' value={state.mobile}
                onChange={(e) => {
                  console.log(e.target.id)
                  setState({ ...state, [e.target.id]: e.target.value })
                }}>
              </MDBInput>
            </MDBInputGroup>


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
              onClick={handleSubmit}>Sign Up
            </MDBBtn>

            <div className="text-center">
              <p>Already Register? <a href="/login">Login</a></p>
            </div>

          </MDBContainer>
        </div>
      </div>
    </>
  )
}

export default Signup