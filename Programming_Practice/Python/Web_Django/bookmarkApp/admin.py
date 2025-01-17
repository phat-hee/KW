from django.contrib import admin
from bookmarkApp.models import Bookmark

# Register your models here.


class BookmarkAdmin(admin.ModelAdmin):
    list_display = ("title", "url")


admin.site.register(Bookmark, BookmarkAdmin)
