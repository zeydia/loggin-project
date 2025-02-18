import { useState } from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Login from './pages/login/Login'
import Signup from './pages/signup/Signup'
import Home from './pages/home/Home'
import User from './pages/dashboard/User'
import Admin from './pages/dashboard/Admin'

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/signup" element={<Signup/>} />
          <Route path="/user" element={<User/>}/>
          <Route path="/admin" element={<Admin/>}/>
        </Routes>      
    </BrowserRouter>
  )
}

export default App
