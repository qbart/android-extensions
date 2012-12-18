package com.softkiwi.android.ormlite;

import java.util.HashMap;
import java.util.Map;
import android.database.SQLException;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.EnumStringType;

public class LowercaseEnumStringPersister extends EnumStringType {

	private static final LowercaseEnumStringPersister singleTon = new LowercaseEnumStringPersister();

	public static LowercaseEnumStringPersister getSingleton() {
		return singleTon;
	}

	private LowercaseEnumStringPersister() {
		super(SqlType.STRING, new Class<?>[] { Enum.class });
	}

	protected LowercaseEnumStringPersister(SqlType sqlType, Class<?>[] classes) {
		super(sqlType, classes);
	}

	@Override
	public Object makeConfigObject(FieldType fieldType) throws SQLException {
		Map<String, Enum<?>> enumStringMap = new HashMap<String, Enum<?>>();
		Enum<?>[] constants = (Enum<?>[]) fieldType.getType().getEnumConstants();
		if (constants == null) {
			throw new SQLException("Field " + fieldType + " improperly configured as type " + this);
		}
		for (Enum<?> enumVal : constants) {
			enumStringMap.put(enumVal.name().toLowerCase(), enumVal);
		}
		return enumStringMap;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object obj) {
		Enum<?> enumVal = (Enum<?>) obj;
		return enumVal.name().toLowerCase();
	}

}
