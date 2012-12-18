Android Extensions
==========================

Extensions for various stuff.

Contains few classes:

* `JsonObjectOrmLitePersister` - custom data persister which allows to serialize and deserialize JsonObject (GSON lib) into database - uses `text` as column type

* `LowercaseEnumStringPersister` - custom data persister which converts all `enum` values into lowercased strings

* `GsonJsonObjectAdapter` - adapter for JsonObject (GSON) - allows you to keep JsonObject in your models as a field (if you don't need to convert all your nested objects from JSON into java classes you can keep them in JsonObject)