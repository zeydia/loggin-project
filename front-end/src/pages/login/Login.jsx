import React from 'react'
import { MDBContainer, MDBInput, MDBBtn } from 'mdb-react-ui-kit'
import axios from 'axios'

const Login = () => {

  const [state, setState] = React.useState({
    username: '',
    password: '',
    error: ''
  })

  const handleSubmit = async () => {
    const { username, password } = state

    try {
      if (state.username === '' || state.password === '') {
        setState({ ...state, error: 'Please fill in all fields' })
      }

      const response = await axios.post('', { username, password })
      console.log()
      history('/dashboard')

    } catch (error) {
      console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
    }

  }


  return (
    <>
      <a href='/' className=''>
        <MDBBtn>HOME</MDBBtn>
      </a>
      <div className="d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4" style={{ width: "400px", height: "auto" }}>
          <MDBContainer className="p-3">
            {/* LOGIN TITLE */}
            <h2 className="text-center mb-4">Login</h2>

            {/* USERNAME */}
            <MDBInput wrapperClass='mb-3' id='username' label='username' type='text' value={state.username}
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

            {/* ERROR */}
            {state.error && <p className="text-danger text-center mb-4">{state.error}</p>}

            {/* SUBMIT */}
            <MDBBtn className='mb-3'
              style={{ height: '40px', width: '100%' }}
              onClick={handleSubmit}>Login
            </MDBBtn>

            <div className="text-center">
              <p>Not a member? <a href="/signup">Sign up</a></p>
            </div>

          </MDBContainer>
        </div>
      </div>
    </>
  )
}

export default Login