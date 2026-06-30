from django.urls import path, include
from rest_framework import routers

from .views import PostListView, PostModelViewSet, PostRetrieveView
from rest_framework.authtoken.views import obtain_auth_token

app_name = 'posts'

router_post = routers.DefaultRouter()
router_post.register('', PostModelViewSet)

urlpatterns = [
    # Generic Views 실습용
    # path('', PostListView.as_view()),
    # path('<int:pk>/', PostRetrieveView.as_view()),
    
    # 토큰 발급 API
    path('login/', obtain_auth_token),

    # ViewSet 실습 때 사용할 코드
    path('', include(router_post.urls)),

]