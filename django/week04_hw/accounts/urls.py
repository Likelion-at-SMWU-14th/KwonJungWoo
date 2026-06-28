from django.urls import path, include
from .views import user, get_single_user

app_name = 'accounts'

urlpatterns = [
    path('', user, name='user'),
    path('<int:id>/', get_single_user, name='get_single_user')
]