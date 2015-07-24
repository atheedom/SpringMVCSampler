package com.springmvcsampler.web.controller;

import com.springmvcsampler.web.form.MultiElementForm;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class FreemarkerController {

	private static final String FORM_ELEMENTS = "freemarker/form_elements";
	
	@RequestMapping(value = "/freemarker/formelements", method = RequestMethod.GET)
	public ModelAndView formElements() {

		ModelAndView modelAndView = new ModelAndView(FORM_ELEMENTS, "multiElementForm", new MultiElementForm());

        modelAndView.addObject("houseTypes", ConvertListToMap(Arrays.asList(MultiElementForm.HouseType.values())));


        HashMap<String, String> houseColour = new HashMap<>();
        houseColour.put("1","Red");
        houseColour.put("2","Blue");
        houseColour.put("3","Green");
        modelAndView.addObject("houseColours", houseColour);


        List<String> houseSizes = new ArrayList<>();
        houseSizes.add("Small");
        houseSizes.add("Medium");
        houseSizes.add("Large");
        modelAndView.addObject("houseSizes", ConvertListToMap(houseSizes));


        return modelAndView;
	}




    @RequestMapping(value="/freemarker/formelements", method = RequestMethod.POST)
    public ModelAndView formElementsView(ModelAndView modelAndView){

        return modelAndView;
    }


    /**
     * Generates a map based on a list of Strings.
     * <p>
     * The Key is the String value.
     * The Value is the capitalization of the String value after first making it lower case.
     * <p>
     * For example: A string DOCUMENTS will generate the key/value pair: DOCUMENTS:Documents.
     * <p>
     * NOTE: the intended purpose of this method is to generate data that will be used to
     * populate a drop in a form from an enum type. However any list of strings can be used.
     *
     * @param values list of strings
     */
    public synchronized static Map<String, String> ConvertListToMap(List<?> values) {
        Map<String, String> mappedValues = new LinkedHashMap<>();
        values.stream().forEach(e -> mappedValues.put(e.toString(), WordUtils.capitalize(e.toString().toLowerCase())));
        return mappedValues;
    }

}
