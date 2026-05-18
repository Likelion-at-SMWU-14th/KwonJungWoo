from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views import View
from .models import Books

# Create your views here.
def book_list(request):
    books = Books.objects.all()

    context = {
        'books': books,
    }

    return render(request, 'book_list.html', context)

class book_detail(View):
    def get(self, request, id):
        book = Books.objects.get(id = id)

        context = {
            'book' : book
        }
        
        return render(request, 'book_detail.html', context)