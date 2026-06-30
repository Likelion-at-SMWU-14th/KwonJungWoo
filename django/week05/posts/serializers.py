from rest_framework.serializers import ModelSerializer

from .models import Post


class PostModelSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__'


class PostListSerializer(PostModelSerializer):
    pass
    # 부모를 그대로 사용 / 몇 개만 골라서 커스텀 할 수도 있음


class PostRetrieveSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        depth = 1


class PostCreateSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        fields = ['image', 'content']