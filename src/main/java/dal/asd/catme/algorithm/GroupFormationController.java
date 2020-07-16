package dal.asd.catme.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/surveyGroups")
public class GroupFormationController {

	private static final Logger logger = LoggerFactory.getLogger(GroupFormationController.class);

	IListGroups listGroups = new ListGroupsServiceImpl();

	@RequestMapping("listGroups")
	public String listGroups(Model model) {
		logger.info("*** GroupFormationController - listGroups method invoked**");
		model.addAttribute("groups",listGroups.listGroups(12));
		return "listGroups";
	}
	
	@RequestMapping("formGroups")
	public String formGroups(Model model) {
		logger.info("*** GroupFormationController - formGroups method invoked**");
		model.addAttribute("groups",listGroups.listGroups(12));
		return "listGroups";
	}
}
