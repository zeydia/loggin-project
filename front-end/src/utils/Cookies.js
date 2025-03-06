import Cookies from 'js-cookie'

export const setCookie = (cookie, token) => {
    Cookies.set(cookie, token, { expires: 1, path: '/', secure: true, sameSite: 'strict' });
}

export const getCookie = (cookie) => {
    return Cookies.get(cookie);
}

export const removeCookie = (cookie) => {
    Cookies.remove(cookie, { path: '/' });
}