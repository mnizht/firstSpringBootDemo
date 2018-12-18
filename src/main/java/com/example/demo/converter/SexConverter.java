package com.example.demo.converter;

import javax.persistence.AttributeConverter;

import com.example.demo.enumeration.SexEnum;

public class SexConverter implements AttributeConverter<SexEnum, Integer> {

	//将枚举转化为数据库列
	@Override
	public Integer convertToDatabaseColumn(SexEnum sex) {
		return sex.getId();
	}
	
	//将数据库列转化为枚举
	@Override
	public SexEnum convertToEntityAttribute(Integer id) {
		return SexEnum.getEnumById(id);
	}

}
