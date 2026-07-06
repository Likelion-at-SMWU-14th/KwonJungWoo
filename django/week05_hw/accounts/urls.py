from django.urls import path, include
from rest_framework import routers
from rest_framework.authtoken.views import obtain_auth_token

from .views import UserModelViewSet

app_name = 'accounts'

router_accounts = routers.DefaultRouter()
router_accounts.register('', UserModelViewSet)
                     
urlpatterns = [
    path('login/', obtain_auth_token),
    path('', include(router_accounts.urls)),
]