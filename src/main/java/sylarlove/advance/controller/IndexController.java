/**
 * 
 */
package sylarlove.advance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午10:42:07
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(){
		return "redict:/index";
	}
}
