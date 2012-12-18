package com.softkiwi.android.ormlite;

import java.sql.SQLException;
import android.util.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.support.DatabaseResults;

/**
 * Defines custom data persister for OrmLite for JsonObject class.
 * 
 * JsonObject is saved into database as long string. When OrmLite reads database it parses string into JsonObject. If parsing fails then null is returned.
 * 
 */
public class JsonObjectOrmLitePersister extends BaseDataType {

	private static final JsonObjectOrmLitePersister instance = new JsonObjectOrmLitePersister();

	private JsonParser parser = new JsonParser();

	public static JsonObjectOrmLitePersister getSingleton() {
		return instance;
	}

	protected JsonObjectOrmLitePersister() {
		super(SqlType.LONG_STRING, new Class[0]);
	}

	private JsonObject fromString(String data) {
		try {
			if (data != null)
				return (JsonObject) parser.parse(data);

		} catch (Exception e) {
			Log.e(getClass().getSimpleName(), "parsing failed", e);
		}

		return null;
	}

	@Override
	public Object parseDefaultString(FieldType fieldType, String defaultStr) {
		return fromString(defaultStr);
	}

	@Override
	public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
		return fromString(results.getString(columnPos));
	}

	@Override
	public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
		return super.sqlArgToJava(fieldType, sqlArg, columnPos);
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
		if (javaObject != null)
			return javaObject.toString();

		return null;
	}

}