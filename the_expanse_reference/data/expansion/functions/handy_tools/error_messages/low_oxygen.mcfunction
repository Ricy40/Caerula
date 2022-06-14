execute as @s run scoreboard players add @s title_timer 1
title @s subtitle {"text":" "}
execute as @s[scores={title_timer=30..}] run title @s title {"translate":"exp_screentxt_lowoxygen_title","color":"red","bold":true}
execute as @s[scores={title_timer=30..}] run scoreboard players set @s title_timer 0
