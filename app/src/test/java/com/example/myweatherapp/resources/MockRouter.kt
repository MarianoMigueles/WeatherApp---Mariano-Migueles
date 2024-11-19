package com.example.myweatherapp.resources

import com.example.myweatherapp.router.IRouter
import com.example.myweatherapp.router.NavTarget

class MockRouter : IRouter {
    override fun navigate(route: NavTarget) {
        println("navigate to: ${ route.id }" )
    }
}