from django.contrib.auth.models import User

from rest_framework.decorators import api_view
from rest_framework.response import Response

from .serializers import UserModelSerializer


@api_view(['GET', 'POST'])
def user(request):
    if request.method == 'GET':
        queryset = User.objects.all()
        serializer = UserModelSerializer(queryset,many=True)
        return Response(serializer.data)
    
    if request.method == 'POST':
        user = UserModelSerializer(data=request.data)
        if user.is_valid():
            user.save()
            return Response(user.data, status=201)
    return Response(user.errors, status=400)


@api_view(['GET', 'PATCH', 'DELETE'])
def get_single_user(request, id):
    try:
        query = User.objects.get(id=id)
    except User.DoesNotExist:
        return Response({"error": "User not found"}, status=404)

    if request.method == 'GET':
        user = UserModelSerializer(query)
        return Response(user.data, status=200)
    
    if request.method == 'PATCH':
        user = UserModelSerializer(query, data=request.data, partial=True)
        if user.is_valid():
            user.save()
            return Response(user.data, status=200)
        return Response(user.errors,status=400)

    if request.method == 'DELETE':
        query.delete()
        return Response(status=204)