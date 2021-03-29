module.exports = {
    "env": {
        "browser": true,
        "es6": true,
        "node": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:prettier/recommended"
    ],
    "globals": {
        "Atomics": "readonly",
        "SharedArrayBuffer": "readonly"
    },
    "parser": "vue-eslint-parser",
    "parserOptions": {
        "parser": "babel-eslint",
        "ecmaVersion": 2018,
        "sourceType": "module"
    },
    "plugins": [
        "vue"
    ],
    "rules": {
        "no-unexpected-multiline" : "error",
		"no-extra-semi" : "error"
    },
    
};