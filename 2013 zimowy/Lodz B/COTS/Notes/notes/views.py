from pyramid.view import view_config

import osoby

@view_config(route_name='home', renderer='templates/mytemplate.pt')
def my_view(request):
    return {'project': 'Notes'}

@view_config(route_name='widok', renderer='templates/widok.pt')
def widok_testowy(request):
    return request.matchdict

@view_config(route_name='osoby', renderer='templates/osoby.mak')
def widok_osob(request):
    return {"osoby": osoby.read_users()}
