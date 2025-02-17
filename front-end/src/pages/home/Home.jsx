import React from 'react'

const Home = () => {
  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '500px', height: '400px'}}>
                <h2 className="mb-4 text-center">Welcome to Home Page</h2>
                <p className="text-center">Hello, I am Elijah21!<br/> 
                    Welcome to my loggin website write in <span className="">Reactjs</span> and <span>Spring Boot</span>. </p>
                <p className="text-center">Please Login or Register to access the website</p>
                <div className="text-center">
                    <a href="/login">
                        <button type="button" className="btn btn-primary m-3">
                        Login
                        </button>
                    </a>
                    <a href="/signup" className="text-white text-decoration-none">
                        <button type="button" className="btn btn-primary m-3" >
                            Signup
                        </button>
                    </a>
                </div>
            </div>
        </div>
  )
}

export default Home