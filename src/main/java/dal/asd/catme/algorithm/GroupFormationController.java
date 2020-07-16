package dal.asd.catme.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;


@Controller
@RequestMapping("/surveyGroups")
public class GroupFormationController {

	private static final Logger logger = LoggerFactory.getLogger(GroupFormationController.class);

	IListGroupsService listGroups;
	IGroupFormationService formGroup;
	
	IAlgorithmModelAbstractFactory modelAbstractFactory =  BaseAbstractFactoryImpl.instance().makeAlgorithmModelAbstractFactory();
	IAlgorithmAbstractFactory algorithmAbstractFactory = BaseAbstractFactoryImpl.instance().makeAlgorithmAbstractFactory();

	@RequestMapping("listGroups")
	public String listGroups(Model model) {
		logger.info("*** GroupFormationController - listGroups method invoked**");
		listGroups=algorithmAbstractFactory.makeListGroupService();
		model.addAttribute("groups",listGroups.listGroups(12));
		return "listGroups";
	}
	
	@RequestMapping("formGroups")
	public String formGroups(Model model) {
		logger.info("*** GroupFormationController - formGroups method invoked**");
		formGroup=algorithmAbstractFactory.makeGroupFormationService();
		model.addAttribute("groups",formGroup.formGroups());
		return "listGroups";
	}
}
