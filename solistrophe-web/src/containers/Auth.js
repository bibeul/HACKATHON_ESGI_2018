import decode from 'jwt-decode';

export default class Auth{
    constructor(domain) {
        this.domain = domain || 'http://localhost:3000/api' // API server domain
        this.fetch = this.fetch.bind(this)
        this.login = this.login.bind(this)
        this.getProfile = this.getProfile.bind(this)
    }

    banUser(accountId){
        let newRank = 0;
        accountId = accountId.toString();
        console.log(accountId + '' + newRank);
        return this.fetch(`${this.domain}/Accounts/updateRank?access_token=` + this.getToken(),{
            method: 'POST',
                body: JSON.stringify({
                accountId,
                newRank
            })
        }).then(res => {
            return Promise.resolve(res);
        })
    }

    login(username, password) {
        // Get a token from api server using the fetch api
        let result = this.fetch(`${this.domain}/Accounts/login`, {
            method: 'POST',
            body: JSON.stringify({
                username,
                password
            })
        }).then(res => {
            this.setToken(res.id) // Setting the token in localStorage
            return this.fetch(`${this.domain}/Accounts/`+ res.userId + '?access_token=' + this.getToken(), {
                method: 'GET'
            }).then(res => {
                if(res.rank < 2){
                    this.setToken('');
                    return Promise.reject('Wrong Rank');
                }
                return Promise.resolve(res);
            })
        })
        console.log(result);
        return result;
    }

    register(username, email, password, firstname, lastname, phoneNumber, address) {
        // Get a token from api server using the fetch api
        return this.fetch(`${this.domain}/Accounts`, {
            method: 'POST',
            body: JSON.stringify({
                username,
                email,
                password,
                lastname,
                firstname,
                phoneNumber,
                address
            })
        }).then(res => {
            return Promise.resolve(res);
        })
    }

    loggedIn() {
        const token = this.getToken() // GEtting token from localstorage
        return !!token && !this.isTokenExpired(token)
    }

    isTokenExpired(token) {
        try {
            const decoded = decode(token);
            if (decoded.exp < Date.now() / 1000) { // Checking if token is expired.
                return true;
            }
            else
                return false;
        }
        catch (err) {
            return false;
        }
    }

    setToken(idToken) {
        // Saves user token to localStorage
        localStorage.setItem('id_token', idToken)
    }

    getToken() {
        // Retrieves the user token from localStorage
        return localStorage.getItem('id_token')
    }

    logout() {
        // Clear user token and profile data from localStorage
        localStorage.removeItem('id_token');
    }

    getProfile() {
        // Using jwt-decode npm package to decode the token
        return decode(this.getToken());
    }


    fetch(url, options) {
        // performs api calls sending the required authentication headers
        const headers = {
            'Content-Type': 'application/json'
        }

        // Setting Authorization header
        if (this.loggedIn()) {
            headers['Authorization'] = 'Bearer ' + this.getToken()
        }

        return fetch(url, {
            headers,
            ...options
        })
            .then(this._checkStatus)
            .then(response => response.json())
    }

    fetchDelete(url, options) {
        // performs api calls sending the required authentication headers
        const headers = {}

        // Setting Authorization header
        if (this.loggedIn()) {
            headers['Authorization'] = 'Bearer ' + this.getToken()
        }

        return fetch(url, {
            headers,
            ...options
        })
            .then(this._checkStatus)
    }

    fetchForm(url, options){
        const headers = {}

        // Setting Authorization header
        if (this.loggedIn()) {
            headers['Authorization'] = 'Bearer ' + this.getToken()
        }

        return fetch(url, {
            headers,
            ...options
        })
            .then(this._checkStatus)
    }

    _checkStatus(response) {
        // raises an error in case response status is not a success
        if (response.status >= 200 && response.status < 300) { // Success status lies between 200 to 300
            return response
        } else {
            var error = new Error(response.statusText)
            error.response = response
            throw error
        }
    }
}